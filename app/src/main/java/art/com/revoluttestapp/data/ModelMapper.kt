package art.com.revoluttestapp.data

interface ModelMapper<P, R>  {

    fun mapToDomain (dto: P): R

}