package dados;
//Um atendimento pode estar em um dos estados (status): PENDENTE,
//EXECUTANDO, FINALIZADO, CANCELADO. Quando um atendimento é criado
//fica no estado PENDENTE, quando há uma equipe é alocada fica no estado
//EXECUTANDO, quando o atendimento termina fica no estado FINALIZADO. A
//qualquer momento atendimento pode ser CANCELADO.
public enum StatusAtendimento {
    PENDENTE, EXECUTANDO, FINALIZADO, CANCELADO;
}
