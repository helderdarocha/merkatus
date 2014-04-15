package br.com.argonavis.merkatus;

import br.com.argonavis.merkatus.entity.licitacao.Concurso;
import br.com.argonavis.merkatus.entity.licitacao.Edital;
import br.com.argonavis.merkatus.entity.licitacao.Habilitacao;
import br.com.argonavis.merkatus.entity.licitacao.EditalPregaoEletronico;
import br.com.argonavis.merkatus.entity.licitacao.orgao.ConcursoComprasNet;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        
        Concurso concurso = new ConcursoComprasNet("Edital ComprasNet XYZ");
        persist(concurso);
        
        Edital edital = new EditalPregaoEletronico(concurso, EditalPregaoEletronico.Tipo.COMPRA_DIRETA);
        persist(edital);
        concurso.setEdital(edital);
        merge(edital);
        merge(concurso);
        
        Habilitacao hab1 = new Habilitacao();
        persist(hab1);
        hab1.setDescricao("Prova de regularidade FGTS");
        merge(hab1);
        concurso.addItemHabilitacao(hab1);
        merge(hab1);
        
        concurso.setDataAbertura(new Date().getTime());
        concurso.setNumero("12345");
        merge(concurso);
        
        Sistema sistema = new Sistema();
        sistema.cadastrarConcurso(concurso);
    }
    
    public static void merge(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("br.com.argonavis_merkatus_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("br.com.argonavis_merkatus_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
