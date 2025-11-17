/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author USER
 */
public class ControllerList<T> {
    private List<T> list;

    public ControllerList() {
        this.list = new ArrayList<T>();
    }
    
    public void addList(T element) {
        if (element != null) {
            this.list.add(element);
        }
    }
    
    public boolean deleteList(T element) {
        return this.list.remove(element);
    }
    
    public T getElementList(int index) {
        if (index < 0 || index >= lengthList()) {
            return null;
        }
        return this.list.get(index);
    }
    
    public boolean updateListWithIndex(int index, T newElement) {
        if (index < 0 || index >= lengthList()) {
            return false;
        }
        
        this.list.set(index, newElement);
        
        return true;
    }
    
    public List<T> getAllTheLista() {
        return this.list;
    }
    
//    public ControllerList<T> filterListByCountry(Predicate<T> predicate) {
//        List<T> leakedData = this.list.stream()
//                .filter(predicate) // filtramos la lista segun el predicado
//                .collect(Collectors.toList()); //recolecta los datos en una nueva lista
//        
//        ControllerList leakedList = new ControllerList<>(); // construimos una nueva lista para guardar los datos del filtrado
//        leakedList.list = leakedData; //asignamos a esa nueva lista los datos del filtrado
//        
//        return leakedList; //devolvemos la lista
//    }
    // metodo que permite filtrar en una lista mediante un predicado y devuelve una nueva lista con los datos de la condicion
    public ControllerList<T> searcInList(Predicate<T> predicate) {
        
        List<T> leakedData = this.list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        
        ControllerList listDuplicate = new ControllerList<>();
        listDuplicate.list = leakedData;
        
        return listDuplicate;
    }
    
    // verifica si hay una coincidencia entre datos que ingresas con datos que ya existen en la lista develve true o false dependiendo los casos
    public boolean existMatch(Predicate<T> predicate) {
        
        return this.list.stream()
                .anyMatch(predicate);
        
    }
    
    public boolean modicateWihtPredicate(Predicate<T> predicado, T nuevoElemento) {
    
        // 1. Buscamos el índice
        int indiceAModificar = -1;
        for (int i = 0; i < lengthList(); i++) {
            if (predicado.test(getElementList(i))) {
                indiceAModificar = i;
                break; // Lo encontramos
            }
        }

        // 2. Si lo encontramos, usamos el otro método
        if (indiceAModificar != -1) {
            return updateListWithIndex(indiceAModificar, nuevoElemento); // Modifica por índice
        }

        return false; // No se encontró
    }
    
    public int lengthList() {
        return this.list.size();
    }
    
    public boolean isEmptyList() {
        return this.list.isEmpty();
    }
}
