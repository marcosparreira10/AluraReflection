package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;



public class ContainerIoc {
	
	public Object getInstancia(Class<?> tipoFonte) {	
		Stream<Constructor<?>> contrutores = 
				Stream.of(tipoFonte.getDeclaredConstructors()); //filtrar os contrutores
		
		Optional<Constructor<?>> construtorPadrao = 
				contrutores.filter(construtor -> construtor.getParameterCount() == 0) //pegando construtor sem parametros
							.findFirst(); //pegando primeiro objeto, retornando variavel optional
		
		try {
			
			if(construtorPadrao.isPresent()) { //se tiver presente a  instacia e retornada
				
				Object instancia = construtorPadrao.get().newInstance();
				return instancia;
				
			} else {//se nao achar instacia construtor padrao
				Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0]; //se getdeclaredConstructor vai pegar posição zero, do array construtor, nao importa posicao que irei pegar
				
				List<Object> params = new ArrayList<>(); //salvar todos objetos que são objetos q sao parametros, lista parametro
				for(Parameter param : construtor.getParameters()) { //retornar parametro do construtor
					
					Class<?> tipoDoParametro  = param.getType(); // pegar tipo parametro
					params.add(getInstancia(tipoDoParametro));//add array tipo do parametro passando para nossa classe principal getInstancia
				}
				
				return construtor.newInstance(params.toArray());// passar todos tipo de parametro encontrado
			}
			
		} catch (InstantiationException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException e) {
			
			throw new RuntimeException(e);
		}
		
	}
}
