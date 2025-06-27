import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';

const routes: Routes = [
    {
        path: '',
        title: 'Chat app',
        loadChildren: () => import('./features/user/user.module').then(m => m.UserModule)
    },
    {
        path: 'login',
        title: 'Login',
        component: LoginComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
