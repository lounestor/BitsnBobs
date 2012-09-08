function setNote(note, col)
        {
	var output = new Array();
	var iCol = parseInt(col, 10);
	iCol = iCol*10;
	
	switch(note){
	
	case "A": 	
		output[0] = iCol+1;
		output[1] = iCol+2;
		break;
	case "B":
		output[0] = iCol + 1;
		break;
	case "C#":
                              
		break;
	
	case "Dupper":			
		output[0] = iCol+2;
		output[1] = iCol+3;
		output[2] = iCol+4;
		output[3] = iCol+5;
		output[4] = iCol+6;
		break;
		
	case "Dlower":
		output[0] = iCol+1;
		output[1] = iCol+2;
		output[2] = iCol+3;
		output[3] = iCol+4;
		output[4] = iCol+5;
		output[5] = iCol+6;
		break;
		
	case "E":
		output[0] = iCol+1;
		output[1] = iCol+2;
		output[2] = iCol+3;
		output[3] = iCol+4;
		output[4] = iCol+5;
		
		break;
	case "F#":
		output[0] = iCol+1;
		output[1] = iCol+2;
		output[2] = iCol+3;
		output[3] = iCol+4;
		
		
		break;
	
	case "G":
		output[0] = iCol+1;
		output[1] = iCol+2;
		output[2] = iCol+3;
		
		
		
		break;
	}
        var i = 0;
        for (i=1 ; i < 7 ; i++) {
            var cur = iCol + i;
            $("#"+cur).removeClass("circle");
            //$("#"+cur).removeClass("circle_blank");
            $("#"+cur).addClass("blank");
        }
	for (i = 0; i < output.length; i++)
	{       
                
		$("#"+output[i]).removeClass("blank");
		$("#"+output[i]).addClass("circle");
                //$("#"+output[i]).addClass("circle_blank");
                
	}
    }	