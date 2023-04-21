"use strict";
var __spreadArray =
  (this && this.__spreadArray) ||
  function (to, from, pack) {
    if (pack || arguments.length === 2)
      for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
          if (!ar) ar = Array.prototype.slice.call(from, 0, i);
          ar[i] = from[i];
        }
      }
    return to.concat(ar || Array.prototype.slice.call(from));
  };
Object.defineProperty(exports, "__esModule", { value: true });
// Primitives: number, string, boolean, null, undefined
// More complex types: arrays, objects
// Function types, parameters
///////////////////// Primitives /////////////////////
var userName = "Theri";
var age = 24;
var isTrue = true;
var hobbies;
///////////////////// More Complex types /////////////////////
//array
var hobbiesArray;
hobbiesArray = ["Cricket", "Cooking"];
// object
var person;
person = {
  name: "Theri",
  age: 24,
};
// array of objects
var people; // combined two types object & array
people = [
  { name: "Theri", age: 24 },
  { name: "Muthu", age: 25 },
  { name: "Selvam", age: 26 },
];
///////////////////// Type Inference /////////////////////
var course = "Gas Dynamics"; // automatically string type has been set, hover over the variable to confirm
///////////////////// Union Types /////////////////////
var address;
address = 178;
address = "Jaihindpuram";
var personType;
var PersonTypeArray;
///////////////////// Function & Types /////////////////////
function add(a, b) {
  return a + b;
}
function printOutput(value) {
  console.log(value);
}
///////////////////// Generics /////////////////////
function insertArrayAtBeginning(array, value) {
  var newArray = __spreadArray([value], array, true); // gonna insert the value before the array
  console.log(newArray);
  return newArray;
}
var demoArray = [1, 2, 3];
var updatedArray = insertArrayAtBeginning(demoArray, -1); // result [-1, 1, 2, 3]
