package art.com.revoluttestapp.data.network

interface ModelMapper<P, R>  {

    fun mapToDomain (dto: P): R

}