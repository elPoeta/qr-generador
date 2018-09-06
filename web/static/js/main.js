const input = document.querySelector('input');
const btn = document.querySelector('#btn-gen');
const panel = document.querySelector('#panel-img');
const URL = 'QrServer';
btn.addEventListener('click', e =>{
    e.preventDefault();
    let codeQr ={}
    codeQr.code = input.value;
    
    Http.post(URL,codeQr)
            .then(response => response.json())
                .then(data =>{
                    console.log(data);
                 panel.innerHTML = `<img src="${data}"/>`;
            })
            .catch(error =>{
               console.log(error); 
            });  
    
});



