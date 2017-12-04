/**
 * Javascript functions with html implementation
 */

function fib(n) {
	if(n <= 1) return n;
	return fib(n-1) + fib(n-2);
}

function runFib(){
	//alert("HELLO!");
	var display = document.getElementById("fibDisplay");
	var n = document.getElementById("fib").value;
	display.innerHTML = fib(n);
}
document.getElementById("doFib").addEventListener("click", runFib);


var a = [34, 203, 3, 746, 200, 984, 198, 764, 9];
var bubArray = [];
function bubbleSort(a)
{
	var swapped;
	do {
		swapped = false;
		for (var i=0; i < a.length-1; i++) {
			if (a[i] > a[i+1]) {
				var temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				swapped = true;
			}
		}
	} while (swapped);
}

function populateArray(){
	bubArray.push(document.getElementById("bub").value);
}

function runBub(){
	var display = document.getElementById("bubDisplay");
	array = bubbleSort(bubArray);
	var data = "";
	for(var i = 0; i < bubArray.length; i++) {
		data += " " + bubArray[i];
	}
	display.innerHTML = data;
}

document.getElementById("doBub").addEventListener("click", runBub);
document.getElementById("addBub").addEventListener("click", populateArray);


var counter = 0;
function count() {
	counter = counter + 1;
	document.getElementById("count").innerHTML = counter;
}

//document.getElementById("count").addEventListener("mouseover", count);

//document.getElementById("outer").addEventListener("click", function() {
	//alert("IN OUTER!");}, false);

//document.getElementById("middle").addEventListener("click", function() {
	//alert("IN MIDDLE!");}, true);

//document.getElementById("inner").addEventListener("click", function() {
	//alert("IN INNER!");}, true);



function reverseString(str) {
	// Step 1. Use the split() method to return a new array
	var splitString = str.split(""); // var splitString = "hello".split("");
	// ["h", "e", "l", "l", "o"]

	// Step 2. Use the reverse() method to reverse the new created array
	var reverseArray = splitString.reverse(); // var reverseArray = ["h", "e", "l", "l", "o"].reverse();
	// ["o", "l", "l", "e", "h"]

	// Step 3. Use the join() method to join all elements of the array into a string
	var joinArray = reverseArray.join(""); // var joinArray = ["o", "l", "l", "e", "h"].join("");
	// "olleh"

	//Step 4. Return the reversed string
	return joinArray; // "olleh"
}

function runStr(){
	//alert("HELLO!");
	var display = document.getElementById("strDisplay");
	var n = document.getElementById("str").value;
	display.innerHTML = reverseString(n);
}
document.getElementById("revStr").addEventListener("click", runStr);


function factorial(num) {
	// If the number is less than 0, reject it.
	if (num < 0) 
		return -1;

	// If the number is 0, its factorial is 1.
	else if (num == 0) 
		return 1;

	// Otherwise, call the recursive procedure again
	else {
		return (num * factorial(num - 1));
	}
}

function runFac(){
	//alert("HELLO!");
	var display = document.getElementById("facDisplay");
	var n = document.getElementById("fac").value;
	display.innerHTML = factorial(n);
}
document.getElementById("fact").addEventListener("click", runFac);

/*
 * Substring usage

var anyString = 'Mozilla';

-->Displays 'Moz'
console.log(anyString.substring(0, 3));
console.log(anyString.substring(3, 0));

-->Displays 'lla'
console.log(anyString.substring(4, 7));
console.log(anyString.substring(4));
console.log(anyString.substring(7, 4));
 */

function isEven(num) {
	if((num & 1) == 0)
		return true;
	return false;
}

function runEven(){
	var display = document.getElementById("evenDisplay");
	var n = document.getElementById("ev").value;
	display.innerHTML = isEven(n);
}
document.getElementById("isEven").addEventListener("click", runEven);

