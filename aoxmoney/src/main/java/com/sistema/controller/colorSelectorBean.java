package com.sistema.controller;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class colorSelectorBean {
	
	  public String computeColor(int saldo) 
	   {
	      if (saldo == 0) 
	      {
	        return "saldoZerado";
	      } 
	      else if (saldo < 0)
	      {
	        return "saldoNegativo";
	      }
	      else 
	      {
	         return "saldoPositivo";
	      }
	   } 
}
