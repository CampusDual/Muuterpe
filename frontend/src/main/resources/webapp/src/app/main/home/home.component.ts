import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';
import { HomeeventsDetailComponent } from './homeevents-detail/homeevents-detail.component';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    protected dialog: MatDialog,
    private actRoute: ActivatedRoute
  ) {
  }

  ngOnInit() {
  }

  navigate() {
    this.router.navigate(['../', 'login'], { relativeTo: this.actRoute });
  }

  public openDetail(data: any): void {
    this.dialog.open(HomeeventsDetailComponent, {
      height: '330px',
      width: '520px',
      data: data
    });
  }

}
