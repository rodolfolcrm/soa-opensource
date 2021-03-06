package br.com.rodolfomartins.integrador;

/**
 * Exception do Integrador
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class IntegradorException extends RuntimeException
{

   private static final long serialVersionUID = 4707361778069912600L;

   public IntegradorException()
   {
      super();
   }

   public IntegradorException(String message)
   {
      super(message);
   }

   public IntegradorException(String message, Throwable cause)
   {
      super(message, cause);
   }

   public IntegradorException(Throwable cause)
   {
      super(cause);
   }
}
