const attributes = {
	introduction: "introduction", 
    overloading: "overloading", 
    overriding: "overriding", 
    coercion: "coercion",
    inclusion: "inclusion",
    generics: "generics",
    modal: "myModal",
    modalContent: "modal-diagram"
};

const scrollValues = {
		[attributes.introduction] : {normal: "0px", alternate: "0px"},
		[attributes.overloading] : {normal: 10, alternate: 8}
}

let scrollReqs = {
	[attributes.introduction] : {
			normalScroll: ()=>{return scrollValues[attributes.introduction].normal;},
			alternateScroll: ()=>{return scrollValues[attributes.introduction].alternate;}
		},
	[attributes.overloading] : {
			normalScroll: ()=>{return ($(window).height() / scrollValues[attributes.overloading].normal).toString();},
			alternateScroll: ()=>{return (($(window).height() - $(map[getPrevious()]).height()) / scrollValues[attributes.overloading].alternate).toString();}
		},
	[attributes.overriding] : {
			normalScroll: ()=>{return ($(window).height() / 4.95).toString();},
			alternateScroll: ()=>{return (($(window).height() - $(map[getPrevious()]).height()) / 4.05).toString();}
		},
	[attributes.coercion] : {
			normalScroll: ()=>{return ($(window).height() / 3.28).toString();},
			alternateScroll: ()=>{return (($(window).height() - $(map[getPrevious()]).height()) / 2.67).toString();}
		},
	[attributes.inclusion] : {
			normalScroll: ()=>{return ($(window).height() / 2.50).toString();},
			alternateScroll: ()=>{return (($(window).height() - $(map[getPrevious()]).height()) / 2).toString();}
		},
	[attributes.generics] : {
			normalScroll: ()=>{return ($(window).height() / 2).toString();},
			alternateScroll: ()=>{return (($(window).height() - $(map[getPrevious()]).height()) / 1.60).toString();}
		}
};
	
function initModals(){
	mainModal = formatAttribute(attributes.modal);
	mainModalContent = formatAttribute(attributes.modalContent);
	modalElements.push(
		formatModalAttribute(attributes.introduction), 
		formatModalAttribute(attributes.overloading), 
		formatModalAttribute(attributes.overriding), 
		formatModalAttribute(attributes.coercion),
		formatModalAttribute(attributes.inclusion),
		formatModalAttribute(attributes.generics));
}

function initElements(specialInitObject){
	for(i = 0; i < specialInitObject.length; i++){
		specialInitObject[i].elements.push(
			formatAttribute(specialInitObject[i].attribute), 
			formatTabAttribute(specialInitObject[i].attribute),  
			formatButtonAttribute(specialInitObject[i].attribute));
	}
}

function setModalFocus(){
	$('#myInput').focus()
}

function setModalDiagram(element){
	$(mainModalContent).attr("src", diagramMap["#" + $(element).attr("id")]);    
	$(mainModalContent).css("margin-top", diagramFormat["#" + $(element).attr("id")]);
}

function setView(attribute, resize){
	if(getCurrent() == attribute){ 
		console.log(formatButtonIconAttribute("setView()" + attribute));
		setIcon(formatButtonIconAttribute(attribute), "fa fa-minus", "fa fa-plus");
		$("li#home > a").attr("href", map[attribute]);
		console.log($("li#home > a")[0]);
		if(((getPrevious() == null || getPrevious() == "home" )) || 
			(getPrevious() == null || getPrevious() == "home" ) && resize == true){
			$('html, body').animate({scrollTop: scrollReqs[attribute].normalScroll() + "px"}, 800);   
		}else $('html, body').animate({scrollTop: scrollReqs[attribute].alternateScroll() + "px"}, 800);  
	}else{setIcon(formatButtonIconAttribute(attribute), "fa fa-plus", "fa fa-minus"); $("li#home > a").attr("href", "#"); }
}

function collapseAll(){
	if(getCurrent() != "home"){
		for (i = 0; i < iconMap.length; i++)$(iconMap[i]).attr("class", "fa fa-plus");
		myFunction(1);
	}else{
		$("li#home > a").attr("href", "#");
		console.log($("li#home > a")[0]);
	}
}

function setIcon(element, iconA, iconB){ 
	if(getCurrent() == null){
		for (i = 0; i < iconMap.length; i++) { 
			$(iconMap[i]).attr("class", "fa fa-plus");
		}
	}else{
		for (i = 0; i < iconMap.length; i++) { 
		    if(element == iconMap[i]){
		    	$(element).attr("class", iconA);
		    }else{$(iconMap[i]).attr("class", iconB);}
		}
	}
}

function repositionElements(){
	console.log("repositioning...");
	
//	setView("home", true);
//	setView(getCurrent(), true);
	console.log("repositioned!");
}
