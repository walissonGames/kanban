package com.kanban.wall.kanban;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kanban.wall.user.User;
import com.kanban.wall.user.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class KanbanService {
    
    @Autowired
    private KanbanRepository kanbanRepository;

    @Autowired
    private UserRepository userRepository;

    public KanbanDto save(KanbanForm form){
        User user = userRepository.getReferenceById(-1L);
        Kanban kanban = new Kanban(form.name(), user);
        kanbanRepository.save(kanban);

        return new KanbanDto(kanban);
    }

    public KanbanDto getKanban(Long id){
        Kanban kanban = kanbanRepository.getReferenceById(id);
        return new KanbanDto(kanban);
    }

    public KanbanDto updateKanban(KanbanForm form){
        Kanban kanban = kanbanRepository.getReferenceById(form.id());
        kanban.updateData(form.name());
        return new KanbanDto(kanban);
    }

    public void deleteKanban(Long id){
        kanbanRepository.deleteById(id);
    }

    public KanbanMemberDto addTeamMember(Long kanbanId, Long userId){
        Kanban kanban = kanbanRepository.getReferenceById(kanbanId);
        User user = userRepository.getReferenceById(userId);
        kanban.addTeamMember(user);
        return new KanbanMemberDto(kanban, user);
    }

    public List<KanbanMemberDto> getTeamMembers(Long kanbanId){
        Kanban kanban = kanbanRepository.getReferenceById(kanbanId);
        List<KanbanMemberDto> members = kanban.getTeam().stream()
                                              .map(user -> new KanbanMemberDto(kanban, user))
                                              .collect(Collectors.toList());
        return members;
    }
}
