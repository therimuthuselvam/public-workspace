import { Injectable, OnInit } from '@angular/core';

import { UserService } from './user.service';

@Injectable()
export class UsersComponent implements OnInit {
  users!: any[];

  constructor(private _service: UserService) { }

  ngOnInit() {
    this._service.getUsers().subscribe((users: any) => this.users = users);
  }

  deleteUser(user: any) {
    if (confirm("Are you sure you want to delete " + user.name + "?")) {
      let index = this.users.indexOf(user)
      this.users.splice(index, 1);

      this._service.deleteUser(user.id).subscribe(
        (res: any) => null,

        (err: any) => {
          alert("Could not delete the user.");
          this.users.splice(index, 0, user);
        });
    }
  }
}
