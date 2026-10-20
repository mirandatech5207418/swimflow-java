package com.swimflow.service;
import com.swimflow.model.*;import com.swimflow.repo.*;import com.swimflow.api.Requests;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import org.springframework.http.HttpStatus;import java.util.*;
@Service public class ItemService {
 private final ItemRepository repo;public ItemService(ItemRepository repo){this.repo=repo;}
 public SwimItem get(Long id){return repo.findById(id).orElseThrow(()->new BusinessException(HttpStatus.NOT_FOUND,"Item não encontrado"));}
 public List<SwimItem> list(){return repo.findAll();}
 @Transactional public SwimItem create(Requests.Item r){if(r.type()==ItemType.EXERCISE && (r.distanceMeters()==null||r.distanceMeters()<=0))throw new BusinessException(HttpStatus.BAD_REQUEST,"Distância deve ser positiva");if(r.type()!=ItemType.EXERCISE && r.distanceMeters()!=null)throw new BusinessException(HttpStatus.BAD_REQUEST,"Somente EXERCISE tem distância");return repo.save(new SwimItem(r.name(),r.type(),r.distanceMeters(),r.status()));}
 @Transactional public SwimItem addComponent(Long parentId,Long childId){SwimItem parent=get(parentId),child=get(childId);if(parent.type==ItemType.EXERCISE)throw new BusinessException(HttpStatus.CONFLICT,"EXERCISE não aceita componentes");if(reaches(child,parent.id,new HashSet<>()))throw new BusinessException(HttpStatus.CONFLICT,"Composição circular proibida");parent.components.add(child);return repo.save(parent);}
 private boolean reaches(SwimItem node,Long target,Set<Long> visited){if(node.id.equals(target))return true;if(!visited.add(node.id))return false;for(SwimItem child:node.components)if(reaches(child,target,visited))return true;return false;}
 @Transactional(readOnly=true) public boolean available(Long id){return availableItem(get(id),new HashSet<>());}
 public boolean availableItem(SwimItem item,Set<Long> path){if(!path.add(item.id))throw new BusinessException(HttpStatus.CONFLICT,"Ciclo detectado");try{return switch(item.type){case EXERCISE -> item.status==ItemStatus.ACTIVE;case CHOICE -> !item.components.isEmpty() && item.components.stream().anyMatch(child->availableItem(child,path));case WORKOUT -> !item.components.isEmpty() && item.components.stream().allMatch(child->availableItem(child,path));};}finally{path.remove(item.id);}}
}
