package com.example.demo.repositories;

import com.example.demo.dto.*;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;
import com.example.demo.exceptions.BadRequestTypeOrderInvalid;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Repository
public class MeliRepositoryImpl implements MeliRepository{
    List<ProductDTO> products;
    List<TicketDTO> tickets;
    private Integer ticketCount = 0;

    public MeliRepositoryImpl() {
        this.products = parseCSV();
        this.tickets = new ArrayList<>();
    }

    @Override
    public List<ProductDTO> getProducts(ParamsDTO params) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid {
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

        if(params.getOrder() != null){
            productsCopy = orderProducts(productsCopy, params.getOrder());
        }
        if((cont > 2 & params.getOrder() == null) || (params.getQuantity() != null & cont == 2)){
            throw new BadRequestExceedsNumberOfFilters();
        }

        return productsCopy;
    }

    private List<ProductDTO> orderProducts(List<ProductDTO> products,Integer typeOrder) throws BadRequestTypeOrderInvalid {

        Comparator<ProductDTO> prComparator = null;

        switch (typeOrder) {
            //Ord Alfabetico ascendent
            case 0:
                prComparator = Comparator.comparing(ProductDTO::getName, Comparator.naturalOrder());
                break;

            //Alfabetico descendente
            case 1:
                prComparator = Comparator.comparing(ProductDTO::getName, Comparator.reverseOrder());
                break;

            //Mayor a menor precio
            case 2:
                prComparator = Comparator.comparing(ProductDTO::getPrice, Comparator.reverseOrder());
                break;

            //Menor a mayor precio
            case 3:
                prComparator = Comparator.comparing(ProductDTO::getPrice, Comparator.naturalOrder());
                break;

            default:
                //Excepcion no existe el numero de tipo de ordenamiento
                throw new BadRequestTypeOrderInvalid();
        }
        if(prComparator != null)
            products.sort(prComparator);

        return products;
    }

    public int getTicketCount(){
        return this.ticketCount++;
    }

    @Override
    public void updateProductQuantity(Integer productId, Integer quantity) {
        for(ProductDTO prd : this.products){
            if(prd.getProductId().equals(productId)){
                prd.setQuantity(prd.getQuantity() - quantity);
            }
        }
    }

    @Override
    public void saveTicket(TicketDTO ticket) {
        //Validar
        this.tickets.add(ticket);
    }

    @Override
    public ShippingCartDTO getPurchases() {
        ShippingCartDTO pr = new ShippingCartDTO();
        double totalAmount = 0.0;
        for(TicketDTO t : tickets){
            totalAmount = totalAmount + t.getTotal();
        }
        List<TicketDTO> auxCopy = new ArrayList<>(this.tickets);
        pr.addTickets(auxCopy);
        pr.setTotal(totalAmount);
        StatusDTO status = new StatusDTO(200, "La solicitud de compra total del carrito se hizo conn exito");
        pr.setStatusCode(status);
        return pr;
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
