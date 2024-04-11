package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VETEMENT")
public class Vetement implements Serializable {
	@Id
	@Column(name = "CODE")
	private Long idVetement;
	@Column(name = "NOM")
	private String nomVetement;
	private double prix;
	
	private Marque marque;
	
	public Vetement(String nomVetement, double prix,Marque cat) {

		super();
		this.nomVetement = nomVetement;
		this.prix = prix;
		this.setMarque(cat);
		}
		public Marque getMarque() {
		return marque;
		}
		public void setMarque(Marque categorie) {
		this.marque = categorie;
		}

	public Vetement() {
		super();
	}

	public Vetement(String nomVetement, double prix) {
		super();
		this.nomVetement = nomVetement;
		this.prix = prix;
	}

	public Long getIdVetement() {
		return idVetement;
	}

	public void setIdVetement(Long idVetement) {
		this.idVetement = idVetement;
	}

	public String getNomVetement() {
		return nomVetement;
	}

	public void setNomVetement(String nomVetement) {
		this.nomVetement = nomVetement;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Vetement [idVetement=" + idVetement + ", nomVetement=" + nomVetement + ", prix=" + prix + "]";
	}

}