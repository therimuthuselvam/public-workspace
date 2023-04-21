// npx tsc ..\ts\basics.ts // use this command to compile

export { }
// Primitives: number, string, boolean, null, undefined
// More complex types: arrays, objects
// Function types, parameters


///////////////////// Primitives /////////////////////

let userName: string = 'Theri';
let age: number = 24;
let isTrue: boolean = true;
let hobbies: null;


///////////////////// More Complex types /////////////////////

//array
let hobbiesArray: string[];
hobbiesArray = ['Cricket', 'Cooking'];

// object
let person: {
  name: string
  age: number
};

person = {
  name: 'Theri',
  age: 24,
}

// array of objects
let people: {
  name: string;
  age: number;
}[]; // combined two types object & array

people = [
  { name: 'Theri', age: 24 },
  { name: 'Muthu', age: 25 },
  { name: 'Selvam', age: 26 },
];


///////////////////// Type Inference /////////////////////

let course = 'Gas Dynamics'; // automatically string type has been set, hover over the variable to confirm


///////////////////// Union Types /////////////////////

let address: string | number;

address = 178;

address = 'Jaihindpuram';


///////////////////// Assigning Type Aliases /////////////////////

type Person = {
  name: string
  age: number
}

let personType: Person;
let PersonTypeArray: Person[];


///////////////////// Function & Types /////////////////////

function add(a: number, b: number) { // type inferred here (number)
  return a + b;
}

function printOutput(value: any) { // type inferred here (void)
  console.log(value);
}


///////////////////// Generics /////////////////////

// function insertArrayAtBeginning(array: any[], value: any) {
function insertArrayAtBeginning<T>(array: T[], value: T) {
  const newArray = [value, ...array]; // gonna insert the value before the array
  return newArray;
}

const demoArray = [1, 2, 3];

const updatedArray = insertArrayAtBeginning(demoArray, -1); // result [-1, 1, 2, 3]

console.log(updatedArray);

const stringArray = insertArrayAtBeginning(['b', 'c', 'd'], 'a') // result ['a', 'b', 'c', 'd']

console.log(stringArray);


///////////////////// Classes & TypeScript /////////////////////

class Student {
  // firstName: string;
  // lastName: string;
  // age: number;
  // private courses: string[];

  // constructor(first: string, last: string, age: number, courses: string[]) {
  //   this.firstName = first;
  //   this.lastName = last;
  //   this.age = age;
  //   this.courses = courses;
  // }

  constructor(
    public firstName: string,
    public lastName: string,
    public age: number,
    private courses: string[]
  ) { } // shorthand notation of the above commented one

  enroll(courseName: string) { // here we don't use function keyword to create a method
    this.courses.push(courseName);
  }

  getListOfCourses() {
    return this.courses.slice(); // the slice method will return the copy of the courses array, 
    // if we don't return the copy, making changes in the returned courses array would impact the main courses array
  }

}

const student = new Student('Theri', 'Muthu', 24, ['Angular', 'Spring Boot']);

student.enroll('Java');

// console.log(student.courses); // cannot be accessible, since courses is a private property
console.log(student.getListOfCourses()); // result ['Angular', 'Spring Boot','Java']

///////////////////// Working with Interfaces /////////////////////

interface Human {
  firstName: string;
  age: number;
  greet: () => void; //takes no parameter and returns nothing.
}

let Theri: Human;

Theri = {
  firstName: 'Theri',
  age: 24,
  greet() {
    console.log('Good Morning')
  },
};

class Instructor implements Human {
  firstName: string = 'Theri';
  age: number = 24;
  greet: () => void = () => {
    console.log('Good Afternoon')
  };
}

