package com.api.DevOps.Controller;

import com.api.DevOps.Model.Curso;
import com.api.DevOps.Model.Estudiante;
import com.api.DevOps.Model.Task;
import com.api.DevOps.Repository.CursoRepository;
import com.api.DevOps.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class EstudianteController {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping(value = "/estudiantes")
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }

    @PostMapping(value = "/guardarestudiante")
    public String guardarEstudiante(@RequestBody Estudiante estudiante){
        estudianteRepository.save(estudiante);
        return "Estudiante guardado";
    }

    @PutMapping(value = "/actualizarestudiante/{id}")
    public String actualizarEstudiante(@PathVariable long id, @RequestBody Estudiante estudiante){
        Estudiante actualizaEstudiante = estudianteRepository.findById(id).get();
        actualizaEstudiante.setNombre_estudiante(estudiante.getNombre_estudiante());
        estudianteRepository.save(actualizaEstudiante);
        return "Estudiante actalizado";
    }
    @DeleteMapping(value= "eliminarestudiante/{id}")
    public String eliminarEstudiante(@PathVariable long id){
        Estudiante eliminaEstudiante = estudianteRepository.findById(id).get();
        estudianteRepository.delete(eliminaEstudiante);
        return "Estudiante eliminado";
    }
    @PostMapping(value = "/asociarEstudianteConCurso/{estudianteId}/{cursoId}")
    public String asociarEstudianteConCurso(@PathVariable long estudianteId, @PathVariable long cursoId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).get();
        Curso curso = cursoRepository.findById(cursoId).get();
        estudiante.getCursos().add(curso);
        estudianteRepository.save(estudiante);
        return "Estudiante asociado con Curso";
    }

    @GetMapping(value = "/estudiante/{estudianteId}/cursos")
    public Set<Curso> getCursosDelEstudiante(@PathVariable long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return estudiante.getCursos();
    }

}
