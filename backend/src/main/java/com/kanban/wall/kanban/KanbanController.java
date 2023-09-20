package com.kanban.wall.kanban;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/kanban")
public class KanbanController {
    
    @Autowired
    private KanbanService kanbanService;

    @PostMapping
    public ResponseEntity<KanbanDto> create(@RequestBody KanbanForm form, UriComponentsBuilder uriBuilder){
        KanbanDto kanbanDto = kanbanService.save(form);
        URI uri = uriBuilder.path("/api/kanban/{id}").buildAndExpand(kanbanDto.id()).toUri();
        return ResponseEntity.created(uri).body(kanbanDto);
    }

    @PostMapping("/team")
    public ResponseEntity<KanbanMemberDto> addTeamMember(@RequestBody KanbanMemberForm form){
        KanbanMemberDto kanbanMemberDto = kanbanService.addTeamMember(form.kanbanId(), form.userId());
        return ResponseEntity.ok().body(kanbanMemberDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KanbanDto> get(@PathVariable Long id){
        KanbanDto kanbanDto = kanbanService.getKanban(id);
        return ResponseEntity.ok(kanbanDto);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<List<KanbanMemberDto>> getTeam(@PathVariable Long id){
        List<KanbanMemberDto> members = kanbanService.getTeamMembers(id);
        return ResponseEntity.ok(members);
    }

    @PutMapping
    public ResponseEntity<KanbanDto> update(@RequestBody KanbanForm form){
        KanbanDto kanbanDto = kanbanService.updateKanban(form);
        return ResponseEntity.ok(kanbanDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        kanbanService.deleteKanban(id);
        return ResponseEntity.noContent().build();
    }
}
