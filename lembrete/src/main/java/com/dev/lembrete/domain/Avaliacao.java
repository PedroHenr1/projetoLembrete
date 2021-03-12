package com.dev.lembrete.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Avaliacao
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long avaliacaoId;
    private Byte avaliacaoNota;
    private String avaliacaoComentario;
}
