package com.example.demo.repositories;

import com.example.demo.dto.ProductDTO;
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
    public List<ProductDTO> getProducts() {
        List<ProductDTO> productsCopy = new ArrayList<ProductDTO>(this.products);
        //ProductDTO prod = new ProductDTO("Remera de Boca","Futbol","Nike",9.000,1,"SI","*****");
        //products.add(prod);

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
                Double price = Double.valueOf(cleanPrice(pr[3]));
                ProductDTO product = new ProductDTO(pr[0],pr[1],pr[2],price,Integer.parseInt(pr[4]),pr[5].equals("SI"),pr[6].length());
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
