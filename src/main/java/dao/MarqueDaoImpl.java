package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Marque;
import util.JPAutil;
public class MarqueDaoImpl implements IMarqueDao {
// TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE_vetements");
@Override
public Marque save(Marque cat ) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.persist(cat);
tx.commit();
return cat;
}
@Override
public Marque getMarque(Long id) {
return entityManager.find(Marque.class, id);
}
@Override
public Marque updateMarque(Marque cat) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.merge(cat);
tx.commit();
return cat;
}
@Override
public void deleteMarque(Long id) {
Marque categorie = entityManager.find(Marque.class, id);
entityManager.getTransaction().begin();
entityManager.remove(categorie);
entityManager.getTransaction().commit();
}
@Override
public List<Marque> getAllMarques() {
List<Marque> cats =

entityManager.createQuery("select c from Marque c").getResultList();
return cats;
}
}