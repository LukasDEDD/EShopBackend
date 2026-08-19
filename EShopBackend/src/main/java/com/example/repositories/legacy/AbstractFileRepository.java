package com.example.repositories.legacy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.repositories.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      return new ArrayList<>();
    }

    try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      List<T> data = gson.fromJson(reader, listType);
      return data != null ? data : new ArrayList<>();
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  protected void saveAll(List<T> entities) {
    Path path = Paths.get(filePath);

    try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
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