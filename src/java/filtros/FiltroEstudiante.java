/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;

/**
 *
 * @author Estudiante
 */
public class FiltroEstudiante implements Filter{

    private FilterConfig configuration;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.configuration=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
        String tipo=((HttpServletRequest) request).getSession().getAttribute("tipo").toString();
        
        if(tipo!=null)
        {
            if(!tipo.equals("estudiante"))
            {
               ((HttpServletResponse)response).sendRedirect("/Taller3JavaWeb/faces/SesionInvalida.xhtml");
            }else{
                Estudiante estudiante =(Estudiante)((HttpServletRequest)request).getSession().getAttribute("usuario");
                if(estudiante!=null){                    
                    chain.doFilter(request, response);
                }else{
                    ((HttpServletResponse)response).sendRedirect("/Taller3JavaWeb/faces/SesionInvalida.xhtml");
                }
            
            }
        }
        }catch(NullPointerException e){
            ((HttpServletResponse)response).sendRedirect("/Taller3JavaWeb/faces/SesionInvalida.xhtml");
        }
    }

    @Override
    public void destroy() {
        this.configuration = null;
        
    }
    
}
