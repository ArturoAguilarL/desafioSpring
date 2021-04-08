# desafioSpring

- Realizados puntos 1-8
    - Puntos obligatorios funcionan igual que en la planilla [a link](https://docs.google.com/document/d/1Q9raZkxc_ShAHuVNwFc6Pxke_cDFhrtXyqt4zJGvX7Q/edit)
    - Si se pasa de mas de dos filtros en el getarticles lanza una excepcion.
    - Si en esos dos filtros aparece "quantity" lanza tmb una excepcion.
    - Lanza excepcion si no aparece el ordenamiento correcto.
    
- Punto 8: realizado en memoria, una vez levantado por primera vez el archivo csv
guarda los elementos en una lista y luego cunado se le manda un purchase-request actualiza los productos en memoria.\
    -Mejora: hacerlo de un archivo. Hacer el update en el archivo.
  
- Puntos 10-11-12:
    - newClient funciona como: "api/v1/newClient"
    - getClients funciona como ""api/v1/getClients"
    - opcion de filtrar por provincia: ""api/v1/getClients?province=Buenos%20Aires"
    
- Punto 9: "api/v1/purchase-request/finishBuy" -> GET METHOD
  Al hacer varias compras en una misma sesion(no cerrar el programa) 
  guarda el historial de tickets y al hacer "finishBuy" devuelve todos los tickets con el total.
  Realizado con un GET ya que no recibe ningun parametro y no necesit aun body
  directamente, dada la sesion, trae todo el shipping cart que tenga con su total.
  Mejora: que reciba un clientId para que, dado el clientId me devuelva su shipping cart.
  
- Mejoras a estos puntos: 
    - Que levante de un archivo todos los clientes y los vaya guardando, asi para probar es mas comodo, para probar se 
    hacer post primero para crear el cliente y luego traerlos con get.
      - Hacer una conexion entre estos puntos y el punto 9 del carrito de compras, que cada cliente tenga un carrito de
    compras, y cuando se hace un purchase-request que reciba el clientId
        para poder asignarle a ese client esa compra. Y en el punto 9 cuando me pidan
        obtener el total, devolvera el tota para ese cliente. 