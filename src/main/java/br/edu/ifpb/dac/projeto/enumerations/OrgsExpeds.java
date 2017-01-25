package br.edu.ifpb.dac.projeto.enumerations;

public enum OrgsExpeds {
	
	ABNC("Academia Brasileira de Neurocirurgia"),
	CGPI_DUREX_DPF("Coordenação Geral de Polícia de Imigração da Polícia Federal"),
	CGPI("Coordenação-Geral de Privilégios e Imunidades"),
	CGPMAF("Coordenadoria Geral de Polícia Marítima, Aeronáutica e de Fronteiras"),
	CNIG("Conselho Nacional de Imigração"),
	CNT("Carteira Nacional de Habilitação"),
	COREN("Conselho Regional de Enfermagem"),
	CORECON("Conselho Regional de Economia"),
	CRA("Conselho Regional de Administração"),
	CRAS("Conselho Regional de Assistentes Sociais"),
	CRB("Conselho Regional de Biblioteconomia"),
	CRC("Conselho Regional de Contabilidade"),
	CRE("Conselho Regional de Estatística"),
	CREA("Conselho Regional de Engenharia e Agronomia"),
	CRECI("Conselho Regional de Corretores de Imóveis"),
	CREFIT("Conselho Regional de Fisioterapia e Terapia Ocupacional"),
	CRF("Conselho Regional de Farmácia"),
	CRM("Conselho Regional de Medicina"),
	CRN("Conselho Regional de Nutrição"),
	CRO("Conselho Regional de Odontologia"),
	CRP("Conselho Regional de Psicologia"),
	CRPRE("Conselho Regional de Profissionais de Relações Públicas"),
	CRQ("Conselho Regional de Química"),
	CRRC("Conselho Regional de Representantes Comerciais"),
	CRMV("Conselho Regional de Medicina Veterinária"),
	CSC("Carteira Sede Carpina de Pernambuco"),
	CTPS("Carteira de Trabalho e Previdência Social"),
	DIC("Diretoria de Identificação Civil"),
	DIREX("Diretoria-Executiva"),
	DPMAF("Divisão de Polícia Marítima, Área e de Fronteiras"),
	DPT("Departamento de Polícia Técnica Geral"),
	DST("Programa Municipal DST/Aids"),
	FGTS("Fundo de Garantia do Tempo de Serviço"),
	FIPE("Fundação Instituto de Pesquisas Econômicas"),
	FLS("Fundação Lyndolpho Silva"),
	GOVGO("Governo do Estado de Goiás"),
	I_CLA("Carteira de Identidade Classista"),
	IFP("Instituto Félix Pacheco"),
	IGP("Instituto Geral de Perícias"),
	IICCECF_RO("Instituto de Identificação Civil e Criminal Engrácia da Costa Francisco de Rondônia"),
	IIMG("Inter-institutional Monitoring Group"),
	IML("Instituto Médico-Legal"),
	IPC("Índice de Preços ao Consumidor"),
	IPF("Instituto Pereira Faustino"),
	MAE("Ministério da Aeronáutica"),
	MEX("Ministério do Exército"),
	MMA("Ministério da Marinha"),
	OAB("Ordem dos Advogados do Brasil"),
	OMB("Ordens dos Músicos do Brasil"),
	PCMG("Policia Civil do Estado de Minas Gerais"),
	PMMG("Polícia Militar do Estado de Minas Gerais"),
	POF_ou_DPF("Polícia Federal"),
	POM("Polícia Militar"),
	SDS("Secretaria de Defesa Social (Pernambuco)"),
	SNJ("Secretaria Nacional de Justiça / Departamento de Estrangeiros"),
	SECC("Secretaria de Estado da Casa Civil"),
	SEJUSP("Secretaria de Estado de Justiça e Segurança Pública – Mato Grosso"),
	SES_ou_EST("Carteira de Estrangeiro"),
	SESP("Secretaria de Estado da Segurança Pública do Paraná"),
	SJS("Secretaria da Justiça e Segurança"),
	SJTC("Secretaria da Justiça do Trabalho e Cidadania"),
	SJTS("Secretaria da Justiça do Trabalho e Segurança"),
	SPTC("Secretaria de Polícia Técnico-Científica"),
	SSP("Secretaria de Segurança Pública");
	
	private final String descricao;

    OrgsExpeds(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}