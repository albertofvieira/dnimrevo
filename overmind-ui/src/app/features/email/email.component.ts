import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { EmailService } from 'src/app/core/services/email.service';
import { ToastrService } from 'ngx-toastr';
import { OvermindValidators } from 'src/app/core/utils/overmind-validators';


@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent implements OnInit {
  overmindLoginForm: FormGroup | any;

  constructor(
    private builder: FormBuilder,
    private emailService: EmailService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.overmindLoginForm = this.builder.group({
      nome: new FormControl(
        '',
        [Validators.required]
      ),

      email: new FormControl(
        '',
        Validators.compose([
          Validators.email,
          Validators.required])
      ),

      telefone: new FormControl(
        '',
        [Validators.required]
      ),

      password: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(8),
          OvermindValidators.patternValidator(/\d/, { hasNumber: true }),
          OvermindValidators.patternValidator(/[A-Z]/, { hasCapitalCase: true }),
          OvermindValidators.patternValidator(/[a-z]/, { hasSmallCase: true })
        ])
      ),
      confirmPassword: new FormControl(
        '',
        [Validators.required]
      )
    },
    {
      // check whether our password and confirm password match
      validator: OvermindValidators.passwordMatchValidator
   })
  }

  onSubmit(overmindLoginForm: any) {
    if(overmindLoginForm.senha != overmindLoginForm.senhaConfirmacao){
      this.toastr.error('', 'As senhas precisam ser iguais!');
    } else {
      let w = this.toastr;
      w.warning('', 'Enviando a mensagem. Por favor, aguarde...')
      
      this.emailService.sendEmail(overmindLoginForm).subscribe((response: any) => {
        w.clear()
        if(response){
          this.toastr.success('', 'Mensagem enviada com sucesso!');
        } else {
          this.toastr.warning('', 'Houve um erro ao enviar a mensagem. Por favor, tente novamente!');
        }
      });  
    }
  }
}
