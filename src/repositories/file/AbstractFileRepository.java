package repositories.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import repositories.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileRepository<T> implements Repository<T> {
  protected final String filePath;
  protected final Gson gson;
  protected final Type listType;

  public AbstractFileRepository(String filePath, Type listType) {
    this.filePath = filePath;
    this.listType = listType;
    this.gson = new GsonBuilder()
      .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
      .setPrettyPrinting()
      .create();
  }

  protected List<T> loadAll() {
    File file = new File(filePath);
    if (!file.exists()) return new ArrayList<>();
    try (Reader reader = new FileReader(file)) {
      List<T> data = gson.fromJson(reader, listType);
      return data != null ? data : new ArrayList<>();
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  protected void saveAll(List<T> entities) {
    try (Writer writer = new FileWriter(filePath)) {
      gson.toJson(entities, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<T> findAll() {
    return loadAll();
  }

  @Override
  public void save(T entity) {
    List<T> all = loadAll();
    all.add(entity);
    saveAll(all);
  }


  protected abstract Long getEntityId(T entity);
}
