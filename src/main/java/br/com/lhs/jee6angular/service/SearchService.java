package br.com.lhs.jee6angular.service;

import java.io.Serializable;
import java.util.List;

import br.com.lhs.jee6angular.model.Model;

public interface SearchService extends Serializable {

    List<Model> search(String search);

    List<Model> all();

}
