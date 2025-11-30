package patterns.strategy;

public interface AttackStrategy {
  /**
   * Executa a estrategia de ataque
   * @parem basePower poder base da civilização
   * @return Descricao do resultado do ataque
   */

  String execute(int basePower);

  /**
   * Calcula o modificador de ataque da estrategia
   * @return multiplicador de ataque (1.5 para +50%)
   */
  double getAttackModifier();

  /**
   * Calcula o modificador de defesa da estrategia
   * @return multiplicador de defesa
   */

  double getDefenseModifier();

  /**
   * Retorna o nome da estrategia
   */
  String getName();

}
