package com.ucb.producto.servicio;

public class Greeting implements IGreeting {

    IUserRepository iUserRepository;

    public Greeting(IUserRepository iUserRepository){
        this.iUserRepository = iUserRepository;
    }

    @Override
    public String greet() {
        return "Este es un saludo sobresaliente!";
    } 

}