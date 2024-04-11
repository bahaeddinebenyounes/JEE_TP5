package web;
import java.util.ArrayList;
import java.util.List;
import metier.entities.Marque;
public class MarqueModel {
List<Marque> categories = new ArrayList<>();
public List<Marque> getMarques() {
return categories;
}
public void setMarques(List<Marque> categories) {
this.categories = categories;
}
}