package metier.entities;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Marque implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long idCat;
private String nomCat;
@Temporal( TemporalType.DATE )
private Date dateCreation;
@OneToMany (mappedBy="Marque")
private List<Vetement> vetements;
public Marque() {
super();
}
public Marque(String nomCat, Date dateCreation) {
super();
this.nomCat = nomCat;
this.dateCreation = dateCreation;
}
public Long getIdCat() {
return idCat;
}
public void setIdCat(Long idCat) {
this.idCat = idCat;
}
public String getNomCat() {
return nomCat;
}
public void setNomCat(String nomCat) {
this.nomCat = nomCat;
}
public Date getDateCreation() {
return dateCreation;
}
public void setDateCreation(Date dateCreation) {
this.dateCreation = dateCreation;
}
public List<Vetement> getVetements() {
return vetements;
}
public void setVetements(List<Vetement> produits) {
this.vetements = produits;
}
}