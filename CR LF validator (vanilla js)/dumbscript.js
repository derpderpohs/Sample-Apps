//No one is gonna read this code anyway

//globasl scope for retarded sms flags and messages
var msg, isOk, feedback;
//type-safe
isOK = 1;
feedback = "SMS OK DUMP IT IN THE BOX";

document.addEventListener('DOMContentLoaded', function (event) {
	//grabDumbMsg();
	if (isOK) {
		//ok, print ok
	} else {
		//it sucks, show in label and highlight your crap
		feedback = "Your SMS has issues like you. Checkem";
	}
})

// This is  to grab the dumb thing from the text area
function grabDumbMsg() {
	//set the fking value, and check the dumb sms.
	var msg = document.getElementsByName('dumbbox')[0].value;
	return regexChecker(msg);

}

//for checking with regex. calls setError to highlight problems in the text.
//pass the values along coz reasons
function regexChecker(message) {
	var strMap = new RegExp(/((?<!\r)\n|\r(?!\n))/gm);
	//strMap.test(message);
	var result;
	while ((result = strMap.exec(message)) !== null) {
		//doSomethingWith(result);
		window.alert("caught " + result);
		return false;
	}
	return true;
}

//highlights errors in text based on regexChecker.
function errorChecker() {
	
	if (grabDumbMsg()) {
		//ok, print ok
		window.alert("excellent");
	} else {
		//it sucks, show in label and highlight your crap
		feedback = "Your SMS has issues like you. Checkem";
	}
}