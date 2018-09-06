class Http{

	static async get(url) {
        try{
        const respuesta = await fetch(url, { 
            method: 'GET',
             headers: {
                'Content-type': 'application/json'
            },
            credentials: 'same-origin'
           });
           
        let datos = JSON.parse(await respuesta.text());
        return datos;
          }catch (err) {
         throw new Error(err);
  }
    }
	
	static async post(url, datos) {
        try{    
        const respuesta = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(datos),
             /*credentials: 'include'  */
              credentials: 'same-origin' 
        });
            return respuesta;
        }catch (err) {
         throw new Error(err);
        }
  }      
   	static async put(url, datos) {
        try{    
        const respuesta = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(datos),
             /*credentials: 'include'  */
              credentials: 'same-origin' 
        });
            return respuesta;
        }catch (err) {
         throw new Error(err);
    }
  } 
    
     static async delete(url) {
        try{        
          const respuesta = await fetch(url, {
            method: 'DELETE',
             headers: {
                'Content-type': 'application/json'
            },
            credentials: 'same-origin'
           });
          return respuesta;
        }catch (err) {
          throw new Error(err);
        }
    }
}

