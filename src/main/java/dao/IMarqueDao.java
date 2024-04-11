package dao;
import java.util.List;
import metier.entities.Marque;
public interface IMarqueDao {
public Marque save(Marque cat);
public Marque getMarque(Long id);
public Marque updateMarque(Marque cat);
public void deleteMarque(Long id);
public List<Marque> getAllMarques();
}