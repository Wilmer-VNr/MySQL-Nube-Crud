package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /*
        C create  -> insert
        R read  -> select
        u update  -< update
        D delete  -> delete

        */

        //insertar

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")){
            MongoDatabase database = mongoClient.getDatabase("MiBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("Mi coleccion");
            Document documento = new Document("Nombre", "Kevin")
                    .append("Apellido", "Silva")
                    .append("edad", 21)
                    .append("email", "KP2024@example.com")
                    .append("telefono", "444-333-4444");
            collection.insertOne(documento);
            System.out.println("Datos registrados con exito");

        }


        //leer

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = mongoClient.getDatabase("MiBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("Mi coleccion");

            FindIterable<Document> documentos = collection.find();

            for (Document documento : documentos) {
                //System.out.println(documento.toJson());
                String nombre = documento.getString("Nombre");
                String apellido = documento.getString("Apellido");
                int edad = documento.getInteger("edad");
                String email = documento.getString("email");
                String telefono = documento.getString("telefono");

                System.out.println("*******************************");
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Edad: " + edad);
                System.out.println("Email: " + email);
                System.out.println("Telefono: " + telefono);

            }
        }

        //actualizar
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")){
            MongoDatabase database = mongoClient.getDatabase("MiBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("Mi coleccion");
            Document filtro = new Document("Nombre", "Wilmer");
            Document actualizacion = new Document("$set", new Document("Apellido", "Vargas"));
            UpdateResult resultado = collection.updateOne(filtro, actualizacion);
            System.out.println("Documentos modificados: " + resultado.getModifiedCount());
        }

        //borrar
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")){
            MongoDatabase database = mongoClient.getDatabase("MiBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("Mi coleccion");
            Document filtro = new Document("Nombre", "Wilmer");
            DeleteResult resultado = collection.deleteOne(filtro);
            System.out.println("Documentos borrados: " + resultado.getDeletedCount());

        }
    }
}