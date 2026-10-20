package com.swimflow;
import com.swimflow.api.Requests;import com.swimflow.model.*;import com.swimflow.repo.ItemRepository;import com.swimflow.service.*;import org.junit.jupiter.api.*;import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;
class ItemServiceTest {
 ItemRepository repo=mock(ItemRepository.class);ItemService service=new ItemService(repo);
 @Test void rejectsZeroDistance(){assertThrows(BusinessException.class,()->service.create(new Requests.Item("Crawl",ItemType.EXERCISE,0,ItemStatus.ACTIVE)));}
 @Test void choiceRequiresOneActive(){SwimItem parent=new SwimItem("Escolha",ItemType.CHOICE,null,ItemStatus.ACTIVE);SwimItem a=new SwimItem("A",ItemType.EXERCISE,100,ItemStatus.INACTIVE);SwimItem b=new SwimItem("B",ItemType.EXERCISE,100,ItemStatus.ACTIVE);parent.id=1L;a.id=2L;b.id=3L;parent.components.add(a);parent.components.add(b);assertTrue(service.availableItem(parent,new java.util.HashSet<>()));b.status=ItemStatus.INACTIVE;assertFalse(service.availableItem(parent,new java.util.HashSet<>()));}
 @Test void workoutRequiresAllActive(){SwimItem parent=new SwimItem("Treino",ItemType.WORKOUT,null,ItemStatus.ACTIVE);SwimItem a=new SwimItem("A",ItemType.EXERCISE,100,ItemStatus.INACTIVE);parent.id=1L;a.id=2L;parent.components.add(a);assertFalse(service.availableItem(parent,new java.util.HashSet<>()));}
 @Test void emptyCompositeIsUnavailable(){SwimItem parent=new SwimItem("Treino",ItemType.WORKOUT,null,ItemStatus.ACTIVE);parent.id=1L;assertFalse(service.availableItem(parent,new java.util.HashSet<>()));}
}
