package br.edu.unipam.service;

import br.edu.unipam.entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuarioService {

    @PersistenceContext(name = "pu_todo")
    private EntityManager entityManager;

    public Usuario salvarUsuario(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    public Usuario localizarPorId(Long id) {
        Usuario userBd = entityManager.find(Usuario.class, id);
        return userBd;
    }

    public void remover(Long id) {
        Usuario user = localizarPorId(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public Usuario editar(Usuario usuario) {
        Usuario userBd = localizarPorId(usuario.getId());

        if (userBd != null) {
            entityManager.merge(usuario);
            return usuario;
        }
        return null;
    }

    public List<Usuario> listarTodos ()
    {
        return entityManager.createNamedQuery(Usuario.GET_ALL_USERS, Usuario.class).getResultList();
    }
}
