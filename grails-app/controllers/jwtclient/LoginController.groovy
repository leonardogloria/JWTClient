package jwtclient

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.crypto.MacProvider

import java.security.Key

class LoginController {
    RestBuilder rest = new RestBuilder()


    def index() {
        Key key = MacProvider.generateKey();


        String s = Jwts.builder()
        .setIssuer("1")
        .signWith(SignatureAlgorithm.HS512, "Bugala").compact();

        println "Encoded = ${s}"

        println Jwts.parser().setSigningKey("Bugala").parseClaimsJws(s).getBody().getIssuer()


    }
    def restaurantes(){
        String s = Jwts.builder()
                .setIssuer("1")
                .signWith(SignatureAlgorithm.HS512, "Bugala").compact();
        println "passei aqui"
        def resp = rest.post("http://localhost:8080/JWTServer/restaurante/restaurantes"){
            header 'X-Auth-Token', s
        }


        println  resp.json


    }
    def login(){
        def resp = rest.post("http://localhost:8080/roletaFood/AppAuth/doLogin") {
            json {
                email = "lsilva@credilink.com.br"
                senha = "123456"

            }
        }
        println resp.statusCode

    }

}
