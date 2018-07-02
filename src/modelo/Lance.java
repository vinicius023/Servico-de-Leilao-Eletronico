package modelo;

public class Lance {

	private Membro membro;
	private Double lance;
	
	public Lance(Membro membro, Double lance) {
		super();
		this.membro = membro;
		this.lance = lance;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Double getLance() {
		return lance;
	}

	public void setLance(Double lance) {
		this.lance = lance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lance == null) ? 0 : lance.hashCode());
		result = prime * result + ((membro == null) ? 0 : membro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lance other = (Lance) obj;
		if (lance == null) {
			if (other.lance != null)
				return false;
		} else if (!lance.equals(other.lance))
			return false;
		if (membro == null) {
			if (other.membro != null)
				return false;
		} else if (!membro.equals(other.membro))
			return false;
		return true;
	}
}
