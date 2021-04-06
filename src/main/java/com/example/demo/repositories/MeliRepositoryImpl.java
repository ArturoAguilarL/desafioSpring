package com.example.demo.repositories;

import com.example.demo.dto.ParamsDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MeliRepositoryImpl implements MeliRepository{
    List<ProductDTO> products;

    public MeliRepositoryImpl() {
        this.products = parseCSV();
    }

    @Override
    public List<ProductDTO> getProducts(ParamsDTO params) throws BadRequestExceedsNumberOfFilters {
        List<ProductDTO> productsCopy = new ArrayList<ProductDTO>(this.products);
        int cont = 0;

        //Si params tiene seteado de filtrar por nombre
        if(params.getName() != null) {
            cont++;
            //Saco todos los prods que no tengan el mismo nombre de la lista a devolver.
            productsCopy.removeIf(p -> !p.getName().equals(params.getName()));
        }
        if(params.getCategory() != null){
            cont++;
            productsCopy.removeIf(p -> !p.getCategory().equals(params.getCategory()));
        }
        if(params.getBrand() != null){
            cont++;
            productsCopy.removeIf(p -> !p.getBrand().equals(params.getBrand()));
        }
        if(params.getPrice() != null){
            cont++;
            productsCopy.removeIf(p -> !p.getPrice().equals(params.getPrice()));
        }
        if(params.getQuantity() != null){
            cont++;
            productsCopy.removeIf(p -> !p.getQuantity().equals(params.getQuantity()));
        }
        if(params.getFreeShipping() != null){
            cont++;
            productsCopy.removeIf(p -> !p.getFreeShipping().equals(params.getFreeShipping()));
        }
        if(params.getPrestige() != null){
            cont++;
            productsCopy.removeIf(p -> !p.getPrestige().equals(params.getPrestige()));
        }
        if(cont > 2 || (params.getQuantity() != null & cont == 2)){
            throw new BadRequestExceedsNumberOfFilters();
        }

        return productsCopy;
    }

    public static List<ProductDTO> parseCSV(){
        String splitBy = ",";
        String line = "";
        List<ProductDTO> products = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/dbProductos.csv"));
            while((line = br.readLine()) != null) {
                String[] pr = line.split(splitBy);
                Double price = Double.valueOf(cleanPrice(pr[4]));
                ProductDTO product = new ProductDTO(Integer.parseInt(pr[0]),pr[1],pr[2],pr[3],price,Integer.parseInt(pr[5]),pr[6].equals("SI"),pr[7].length());
                products.add(product);
            }
            br.close();
            return products;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }


    private static String cleanPrice(String price) {
        char[] arr = price.toCharArray();
        StringBuilder result = new StringBuilder();

        for (char c : arr) {
            if (c != '$' & c != '.') {
                result.append(c);
            }
        }
        return result.toString();
    }











}
