import { Injectable, OnDestroy } from '@angular/core';
import { CompatClient, Stomp, StompSubscription } from '@stomp/stompjs';
import { Message } from '../../models/message.model';
import SockJS from 'sockjs-client';

export type ListenerCallBack = (message: Message) => void;

@Injectable({
  providedIn: 'root',
})
export class WebsocketService implements OnDestroy {
  private connection: CompatClient;
  private subscription: StompSubscription | undefined;

  constructor() {
    const socket = new SockJS('http://localhost:8080/furia-chat-websocket');
    this.connection = Stomp.over(socket);
    this.connection.connect({}, () => {
      console.log('Websocket conectado');
    });
  }

  public sendMessage(user: string, message: string): void {
    if (this.connection && this.connection.connected) {
      const payload = { user, message };
      this.connection.send('/app/new-message', {}, JSON.stringify(payload));
    } else {
      console.error('Websocket não está conectado');
    }
  }

  public listen(callback: ListenerCallBack) {
    const waitConnection = () => {
      if (this.connection.connected) {
        this.subscription = this.connection.subscribe(
          '/topics/livechat',
          (msg) => {
            try {
              const parsedMessage: Message = JSON.parse(msg.body);
              callback(parsedMessage);
            } catch (error) {
              console.error('Erro ao parsear:', error, 'Raw:', msg.body);
            }
          }
        );
      } else {
        setTimeout(waitConnection, 100);
      }
    };
    waitConnection();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    if (this.connection) {
      this.connection.disconnect();
    }
  }
}
