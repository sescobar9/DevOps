package com.api.DevOps.Controller;

import com.api.DevOps.Model.Curso;
import com.api.DevOps.Model.Estudiante;
import com.api.DevOps.Model.Task;
import com.api.DevOps.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping(value = "/cursos")
    public List<Curso> cursos(){
        return cursoRepository.findAll();
    }

    @PostMapping(value = "/guardarcurso")
    public String guardarCurso(@RequestBody Curso curso){
        cursoRepository.save(curso);
        return "Curso Guardado";
    }

    @PutMapping(value = "/actualizarcurso/{id}")
    public String actualizarCurso(@PathVariable long id, @RequestBody Curso curso){
        Curso actualizaCurso = cursoRepository.findById(id).get();
        actualizaCurso.setNombre_curso(curso.getNombre_curso());
        cursoRepository.save(actualizaCurso);
        return "Curso actualizado";
    }
    @DeleteMapping(value= "eliminarcurso/{id}")
    public String eliminarCurso(@PathVariable long id){
        Curso eliminaCurso = cursoRepository.findById(id).get();
        cursoRepository.delete(eliminaCurso);
        return "Curso eliminado";
    }

    @GetMapping(value = "/curso/{cursoId}/estudiantes")
    public Set<Estudiante> getEstudiantesDelCurso(@PathVariable long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        return curso.getEstudiantes();
    }

}
