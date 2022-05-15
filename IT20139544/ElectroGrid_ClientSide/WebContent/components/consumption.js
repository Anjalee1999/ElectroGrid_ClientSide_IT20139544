$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateConsumptionForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidconsumptionIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ConsumptionApi",
		type : t,
		data : $("#formConsumption").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onConsumptionSaveComplete(response.responseText, status);
		}
	});
}); 

function onConsumptionSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidconsumptionIDSave").val("");
	$("#formConsumption")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidconsumptionIDSave").val($(this).closest("tr").find('#hidconsumptionIDUpdate').val());     
	$("#customerID").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#presentReading").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#previousReading").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#consumptionUnit").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ConsumptionApi",
		type : "DELETE",
		data : "consumptionID=" + $(this).data("consumptionid"),
		dataType : "text",
		complete : function(response, status)
		{
			onConsumptionDeletedComplete(response.responseText, status);
		}
	});
});

function onConsumptionDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateConsumptionForm() {  
	// CustomerID
	if ($("#customerID").val().trim() == "")  {   
		return "Insert Customer ID.";  
		
	} 
	
	 //PresentReading
	if ($("#presentReading").val().trim() == "")  {   
		return "Insert Present Reading.";  
	} 
	
	
	//PreviousReading
	if ($("#previousReading").val().trim() == "")  {   
		return "Insert Previous Reading ."; 
		 
	}
	
	//ConsumptionUnit
	if ($("#consumptionUnit").val().trim() == "")  {   
		return "Insert Consumption Unit ."; 
		 
	}
	
		

	 
	 return true; 
	 
}
