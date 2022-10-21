
let fname,lname,bdate,userid,pword,actno,gende,bal;



function addcustomer()
{
    fname=$("#fname").val();
    lname=$("#lname").val();
    bdate=$("#bdate").val();
    userid=$("#userid").val();
    pword=$("#pword").val();
    actno=$("#actno").val();
    gende=$("#gende").val();
    bal=$("#bal").val();
    
    
    let data = {
                fname:fname,
                lname:lname,
                bdate:bdate,
                userid:userid,
                pword:pword,
                actno:actno,
                gende:gende,
                bal:bal
                
            };
    let xhr=$.post("RegistrationControllerServlet",data,processResponse);   
            
            xhr.fail(handleError);
}
function processResponse(responseText,textStatus,xhr)
{
    let str=responseText.trim();
 
        swal("Success","Registration Successfully Done! You Can Now Login","success");
        setTimeout(redirectUser,3000);
}
function handleError(xhr)
{
    swal("Error","Problem IN Server Communication :"+xhr.statusText,"error");
}