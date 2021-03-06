import { Component, Output, EventEmitter} from '@angular/core';
import { Todo } from '../todo';

@Component({
  selector: 'app-todo-list-header',
  templateUrl: './todo-list-header.component.html',
  styleUrls: ['./todo-list-header.component.css']
})
export class TodoListHeaderComponent {

  newTodo: Todo = new Todo();

  @Output()
  add: EventEmitter<Todo> = new EventEmitter();

  addTodo(){
    this.add.emit(this.newTodo);
    this.newTodo = new Todo();
  }
  /*
  Instead of injecting our tododataservice into this component to 
  save the new Todo, we emit an add event and pass the new todo 
  as an argument
  */

  

}
