import {
  AfterViewChecked,
  Component,
  ElementRef,
  ViewChild,
} from '@angular/core';
import { CardData } from '../../models/card.model';
import { CommonModule } from '@angular/common';
import { CardComponent } from '../../shared/components/card/card.component';
import { WebsocketService } from '../../services/websocket/websocket.service';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Message } from '../../models/message.model';

@Component({
  selector: 'app-home',
  imports: [CommonModule, CardComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements AfterViewChecked {
  cardItems: CardData[] = [
    {
      title: 'Participe do chat',
      description: 'Mostre a força da torcida e interaja durante os jogos',
    },
    {
      title: 'Proximos jogos',
      description: 'Fnatic, 05/05/2025 ás 20horas',
    },
    {
      title: 'Proximos jogos',
      description: 'Fnatic, 05/05/2025 ás 20horas',
    },
  ];
  messages: Message[] = [];
  isConnected: boolean = false;
  @ViewChild('chatMessages') private chatMessagesContainer!: ElementRef;

  form: FormGroup = new FormGroup({
    user: new FormControl<string>('', Validators.required),
    text: new FormControl<string>(
      { value: '', disabled: true },
      Validators.required
    ),
  });

  constructor(private websocketService: WebsocketService) {}

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  connect(): void {
    const user = this.form.get('user')?.value?.trim();
    if (user) {
      this.websocketService.listen((message) => {
        this.messages.push(message);
      });

      this.isConnected = true;
      this.form.get('user')?.disable();
      this.form.get('text')?.enable();
    }
  }

  disconnect(): void {
    this.websocketService.ngOnDestroy();
    this.isConnected = false;
    this.form.get('user')?.enable();
    this.form.get('text')?.disable();
  }

  sendMessage(): void {
    const user = this.form.get('user')?.value;
    const text = this.form.get('text')?.value;
    if (user && text) {
      this.websocketService.sendMessage(user, text);
      this.form.get('text')?.reset();
    }
  }

  private scrollToBottom(): void {
    if (this.chatMessagesContainer) {
      const element = this.chatMessagesContainer.nativeElement;
      element.scrollTop = element.scrollHeight;
    }
  }

  //click(): void {
  //this.sendMessage(this.form.value.user, this.form.value.text);
  //this.form.reset({});
  //}
}
