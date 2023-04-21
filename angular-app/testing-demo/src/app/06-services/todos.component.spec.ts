import { Observable, of, throwError } from 'rxjs';
import { TodoService } from './todo.service';
import { TodosComponent } from './todos.component';

describe('TodosComponent', () => {
  let component: TodosComponent;
  let service: TodoService;

  beforeEach(() => {
    service = new TodoService();
    component = new TodosComponent(service);
  });

  it('should set the todos property with the items returned from the server', () => {
    let todos = [1, 2, 3];
    // spyOn(service, 'getTodos').and.callFake(() => {
    //   return Observable.from([todos]);
    // }); //Assign // with this we can change the implementation but it's not working, so we have to use the below.

    spyOn(service, 'getTodos').and.returnValue(of(todos)); //Assign // with this we can change the implementation

    component.ngOnInit(); //Act

    expect(component.todos).toBe(todos); //Assert
  });

  it('should call the server to save the changes when a new todo item is added', () => {
    let spy = spyOn(service, 'add').and.callFake(() => {
      return of(); // return Observable.empty(); // it won't work
    });

    component.add();

    expect(spy).toHaveBeenCalled();
  });

  it('should add the new todo returned from the server', () => {
    let todo = { id: 1 };
    spyOn(service, 'add').and.returnValue(of(todo));
    // spyOn(service, 'getTodos').and.callFake(() => {
    //   return Observable.from([todos]);
    // }); // it won't work

    component.add();

    expect(component.todos.indexOf(todo)).toBeGreaterThan(-1);
  });

  it('should set the message property if server returns an error when adding a new todo', () => {
    let error = "error from the server";
    spyOn(service, 'add').and.returnValue(throwError(() => error));

    component.add();

    expect(component.message).toBe(error);
  });

  xit('should call the server to delete the todo item if the user confirms', () => {
    let id = 1;
    spyOn(window, 'confirm').and.returnValue(true);
    let spy = spyOn(service, 'delete').and.returnValue(of());

    component.delete(1);

    // expect(spy).toHaveBeenCalled(); // generic
    expect(spy).toHaveBeenCalledWith(1); // specific - just checking whether the passed value has reached the service or not

  });

  xit('should NOT call the server to delete the todo item if the user cancels', () => {
    let id = 1;
    spyOn(window, 'confirm').and.returnValue(false);
    let spy = spyOn(service, 'delete').and.returnValue(of());

    component.delete(1);

    // expect(spy).toHaveBeenCalled(); // generic
    expect(spy).not.toHaveBeenCalled(); // specific - just checking whether the passed value has reached the service or not

  });

});
