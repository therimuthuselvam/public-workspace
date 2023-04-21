import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService {
  private _url = "http://jsonplaceholder.typicode.com/users";
  private _http!: HttpClient; // should be declared inside the constructor, but due to issues declared outside

  constructor() {
    // TODO document why this constructor is empty
  }

  getUsers() {
    return this._http.get(this._url)
    //.map((res: { json: () => any; }) => res.json());
  }

  deleteUser(userId: any) {
    return this._http.delete(this.getUserUrl(userId))
    //.map((res: { json: () => any; }) => res.json());
  }

  private getUserUrl(userId: string) {
    return this._url + "/" + userId;
  }
}
