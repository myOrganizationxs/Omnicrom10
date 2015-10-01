/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import NodoExceptions.NonexistentEntityException;
import Pojo.Nodo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author ubuntu
 */
public class KategorilerJpaController {

    public KategorilerJpaController() {
        emf = Persistence.createEntityManagerFactory("NodoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nodo kategoriler) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kategoriler);
            em.getTransaction().commit();
        } finally {           if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nodo kategoriler) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kategoriler = em.merge(kategoriler);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kategoriler.getIdNodo();
                if (findKategoriler(id) == null) {
                    throw new NonexistentEntityException("The kategoriler with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nodo kategoriler;
            try {

                kategoriler = em.getReference(Nodo.class, id);
                kategoriler.getIdNodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kategoriler with id " + id + " no longer exists.", enfe);
            }
            em.remove(kategoriler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nodo> findKategorilerEntities() {
        return findKategorilerEntities(true, -1, -1);
    }

    public List<Nodo> findKategorilerEntities(int maxResults, int firstResult) {
        return findKategorilerEntities(false, maxResults, firstResult);
    }

    private List<Nodo> findKategorilerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Nodo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nodo findKategoriler(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getKategorilerCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Nodo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
//Gelen kategori isminine ait id yi donderiyor. Bu method normalde ide tarafından oluşturulmuyor. Kendim yazdim
    public Nodo kategoriIdDonder(String kategoriAdi)
    {
        EntityManager em=getEntityManager();
        try{
            Query query=em.createQuery("SELECT k FROM Nodo k WHERE k.cargo =?").setParameter(1, kategoriAdi);;
        return (Nodo) query.getSingleResult();
        }finally{
            em.close();
        }
    }

}
