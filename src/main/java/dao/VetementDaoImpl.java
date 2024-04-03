package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Vetement;
import util.JPAutil;

public class VetementDaoImpl implements IVetementDao {
	private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE");

	@Override
	public Vetement save(Vetement p) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(p);
		tx.commit();
		return p;
	}

	@Override
	public List<Vetement> VetementsParMC(String mc) {
	List<Vetement> vets =
	entityManager.createQuery("select p from vetement p where p.nomVetement like :mc")
	.setParameter("mc", "%"+mc+"%").getResultList();

	return vets;
	}

	@Override
	public Vetement getVetement(Long id) {
		return entityManager.find(Vetement.class, id);
	}

	@Override
	public Vetement updateVetement(Vetement p) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(p);
		tx.commit();
		return p;
	}

	@Override
	public void deleteVetement(Long id) {
		Vetement produit = entityManager.find(Vetement.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(produit);
		entityManager.getTransaction().commit();
	}
}}